import uiautomator2 as u2

d = u2.connect('emulator-5554') # alias for u2.connect_usb('123456f')
print(d.info)
print(d.app_list_running())

output, exit_code = d.shell("monkey -p com.google.android.calendar -v -v -v 10", timeout=600)
print(exit_code)
print(output)

d.app_info("com.example.testapplication")

output, exit_code = d.shell("monkey -p com.example.testapplication -v -v -v 10", timeout=600)
print(exit_code)
print(output)