import uiautomator2 as u2

d = u2.connect('emulator-5554') # alias for u2.connect_usb('123456f')
print(d.info)
print(d.app_list_running())
d.app_info("com.example.TestApplication")
output, exit_code = d.shell("monkey -p com.google.android.calendar -v -v -v 10", timeout=600)
print(exit_code)
print(output)

output, exit_code = d.shell("monkey -p com.example.TestApplication -v -v -v 10", timeout=600)
print(exit_code)
print(output)