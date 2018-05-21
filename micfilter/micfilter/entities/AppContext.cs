using micfilter.helpers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace micfilter.entities
{
    class AppContext
    {
        private List<OutputDevice> outputDevices = new List<OutputDevice>();
        private List<InputDevice> inputDevices = new List<InputDevice>();
        private OutputDevice currentOutputDevice;
        private InputDevice currentInputDevice;

        internal OutputDevice CurrentOutputDevice
        {
            get
            {
                return currentOutputDevice;
            }
        }

        internal InputDevice CurrentInputDevice
        {
            get
            {
                return currentInputDevice;
            }
        }

        public List<OutputDevice> getOutputDevices()
        {
            return outputDevices;
        }

        public List<InputDevice> getInputDevices()
        {
            return inputDevices;
        }

        public void loadAllDevices()
        {
            inputDevices = DeviceHelper.getAvailableInputDevices();
            outputDevices = DeviceHelper.getAvailableOutputDevices();
        }

        public bool setCurrentOutputDeviceByDeviceId(int deviceId)
        {
            foreach(var device in outputDevices)
            {
                if (device.DeviceId == deviceId)
                {
                    if (currentInputDevice != null)
                    {
                        currentOutputDevice.dispose();
                    }

                    currentOutputDevice = device;
                    return true;
                }
            }
            return false;
        }

        public bool setCurrentInputDeviceByDeviceId(int deviceId, IntPtr handle)
        {
            foreach(var device in inputDevices)
            {
                if (device.DeviceId == deviceId)
                {
                    if (currentInputDevice != null)
                    {
                        currentInputDevice.dispose();
                    }

                    currentInputDevice = device;
                    currentInputDevice.initialize(handle);
                    return true;
                }
            }

            return false;
        }

        public bool setCurrentInputDevice(InputDevice device, IntPtr handle)
        {
            if (device == null) return false;

            if (currentInputDevice != null)
            {
                currentInputDevice.dispose();
            }

            currentInputDevice = device;
            currentInputDevice.initialize(handle);
            return true;
        }

        public bool setCurrentOutputDevice(OutputDevice device)
        {
            if (device == null) return false;

            if (currentOutputDevice != null)
            {
                currentOutputDevice.dispose();
            }

            currentOutputDevice = device;
            currentOutputDevice = device;

            return true;
        }
    }
}
