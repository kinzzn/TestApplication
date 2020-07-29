package com.example.testapplication;

import android.app.usage.StorageStats;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.Build;
import android.os.Message;
import android.os.RemoteException;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;


// ref: https://blog.csdn.net/qq_36356379/article/details/101423914
public class SizeHelper {

    /**
     * Before 8.0 to get size
     * with android.permission.GET_PACKAGE_SIZE in AndroidManifest.xml
     * @param context
     * @param pkgName
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getPkgSize(final Context context, String pkgName) {
        Method method;
        StringBuilder sb = new StringBuilder();
        try {
            method = PackageManager.class.getMethod("getPackageSizeInfo",
                new Class[]{String.class, IPackageStatsObserver.class});
            // 调用 getPackageSizeInfo 方法，需要两个参数：1、需要检测的应用包名；2、回调
            method.invoke(context.getPackageManager(), pkgName,
                new IPackageStatsObserver.Stub() {
                    @Override
                    public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) throws RemoteException {
                        //这里是异步执行，如有需要可以传入handler，通过handler把信息传递给UI线程
                        if (succeeded && pStats != null) {
                            synchronized (SizeHelper.class){
                                String format = "%s：%.3f MB\n";
                                sb.append(String.format(format, "缓存大小",pStats.cacheSize/1024.0/1024.0));
                                sb.append(String.format(format, "数据大小",pStats.dataSize/1024.0/1024.0));
                                sb.append(String.format(format, "应用大小",pStats.codeSize/1024.0/1024.0));
                                sb.append(String.format(format, "应用总大小", (pStats.cacheSize + pStats.codeSize + pStats.dataSize)/1024.0/1024.0));
//                                sb.append("toString(): ");
//                                sb.append(pStats.toString());
                            }
                            Log.d(this.getClass().toString(), sb.toString());
                            Message msg = Message.obtain();
                            msg.obj = sb.toString();
                            msg.what = 1;
                            MainActivity.handler.sendMessage(msg);
                            // Handler使用参考：
                            // https://www.jianshu.com/p/0a274564a4b1
                        }
                    }

                });
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * after 8.0
     * with android.permission.PACKAGE_USAGE_STATS"
     * @param context
     * @param pkgName
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getAppSize(final Context context, String pkgName) {
        StringBuilder sb = new StringBuilder();
        StorageStatsManager storageStatsManager = (StorageStatsManager) context.getSystemService(Context.STORAGE_STATS_SERVICE);
        StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        List<StorageVolume> storageVolumes = storageManager.getStorageVolumes();
        for (StorageVolume item : storageVolumes) {
            String uuidStr = item.getUuid();
            UUID uuid;
            if (uuidStr == null) {
                uuid = StorageManager.UUID_DEFAULT;
            } else {
                uuid = UUID.fromString(uuidStr);
            }
            int uid = getUid(context,pkgName);
            //通过包名获取uid
            StorageStats storageStats = null;
            try {
                storageStats = storageStatsManager.queryStatsForUid(uuid, uid);
                synchronized (SizeHelper.class) {
                    sb.append("缓存大小："+storageStats.getCacheBytes()/1024/1024+"MB\n");
                    sb.append("数据大小："+storageStats.getDataBytes()/1024/1024+"MB\n");
                    sb.append("应用大小："+storageStats.getAppBytes()/1024/1024+"MB\n");
                    sb.append("应用总大小："+(storageStats.getCacheBytes() + storageStats.getDataBytes() + storageStats.getAppBytes())/1024/1024+"MB\n");
                    sb.append("toString(): ");
                    sb.append(storageStats.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static int getUid(Context context, String pakName) {
        try {
            return context.getPackageManager().getApplicationInfo(pakName, PackageManager.GET_META_DATA).uid;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }


}
