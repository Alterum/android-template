For testing the app on real device you need the followinng:
- enable debugging mode n the device: Settings -> About phone -> 7 touches to "Build number";
  back to the settings -> developer options -> USB debugging
- enable installing from unknown sources: Settings -> Security -> Unknown sources
NB! https://developer.android.com/studio/run/device.html#VendorIds
For the ubuntu you need the following:
cat /etc/udev/rules.d/51-android.rules
SUBSYSTEM=="usb", ATTR{idVendor}=="12d1", MODE="0666", GROUP="plugdev"
chmod a+r /etc/udev/rules.d/51-android.rules
Where 12d1 is Huawei vendor ID