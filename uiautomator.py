import uiautomator2 as u2

d = u2.connect('emulator-5554') # alias for u2.connect_usb('123456f')
print(d.info)
d.app_list_running()
d.app_info("com.example.TestApplication")