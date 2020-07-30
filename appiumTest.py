from appium import webdriver
import selenium
package = 'com.example.testapplication'
activity = '.MainActivity'
desired_caps = {}
desired_caps['appPackage'] = 'com.example.testapplication'
desired_caps['appActivity'] = '.MainActivity'
# desired_caps['appPackage'] = 'com.microsoft.sapphire.daily'
# desired_caps['appActivity'] = 'com.microsoft.sapphire.app.activities.SplashActivity'
# desired_caps['appPackage'] = 'com.awesomeproject'
# desired_caps['appActivity'] = '.MainActivity'
desired_caps["autoGrantPermissions"]="true"
desired_caps["platformVersion"]="7.0"
desired_caps['platformName'] = 'Android'
desired_caps['noReset']="true"
desired_caps['newCommandTimeout']="60"
desired_caps['automationName']="UIAutomator2"
desired_caps['uiautomator2ServerInstallTimeout']="20000"
desired_caps['adbExecTimeout']="20000"

driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)
print("[DRIVER SESSION] ",  driver)

# try:
#     el = driver.find_element_by_android_uiautomator('new UiSelector().text("%s")' % "THREAD BLOCK")
#     print(el, type(el))
#     el.click()
# except selenium.common.exceptions.NoSuchElementException as e:
#     print("find_element_by_accessibility_id",e)
import time
# time.sleep(5)
try:
    el = driver.find_element_by_android_uiautomator('new UiSelector().text("%s")' % "NULL POINTER")
    print(el, type(el))
    el.click()
except selenium.common.exceptions.NoSuchElementException as e:
    print("find_element_by_accessibility_id",e)
time.sleep(5)

driver.start_activity(package, activity)
try:
    el = driver.find_element_by_android_uiautomator('new UiSelector().text("%s")' % "CPU OVERLOAD")
    print(el, type(el))
    el.click()
except selenium.common.exceptions.NoSuchElementException as e:
    print("find_element_by_android_uiautomator",e)
time.sleep(5)
driver.start_activity(package, activity)
try:
    el = driver.find_element_by_android_uiautomator('new UiSelector().text("%s")' % "EXCEPTION")
    print(el, type(el))
    el.click()
except selenium.common.exceptions.NoSuchElementException as e:
    print("find_element_by_android_uiautomator",e)

# try: # not supported
#     el = driver.find_element_by_android_data_matcher(name='hasEntry', args=['title', 'Animation'])
#     print(el, type(el))
#     el.click()
# except selenium.common.exceptions.NoSuchElementException as e:
#     print("find_element_by_android_data_matcher",e)


# assertIsNotNone(el)
# print(el)
# options={}
# options['']
# driver.start_recording_screen()


## desktop
# el1 = driver.find_element_by_id("index-kw")
# el1.send_keys("ms")
# el2 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View[3]/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[4]/android.widget.Button")
# el2.click()
# el3 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View[1]/android.view.View/android.widget.Button")
# el3.click()
# driver.back()


# import time
# recordspan = 10
# timespan = 0.1
# present = time.time()
# finish = present+timespan
# import hashlib
# m = hashlib.md5()
# data = str(present)+desired_caps['appPackage']
# m.update(data.encode(encoding='UTF-8'))
# recordid = m.hexdigest()
# # print(time.time())
# driver.save_screenshot("screenshots/"+recordid+"_%.3f.png"%time.time())
# # print(time.time())
# # while time.time()<finish:
# # 	driver.save_screenshot("screenshots/"+recordid+"_%.3f.png"%time.time())
# # click random

# # driver.stop_recording_screen()
# import time
# time.sleep(100)

# driver.quit()
