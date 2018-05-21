using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace micfilter.entities
{
    class Device
    {
        private string deviceName;
        private int deviceId;

        public string DeviceName
        {
            get
            {
                return deviceName;
            }

            set
            {
                deviceName = value;
            }
        }

        public int DeviceId
        {
            get
            {
                return deviceId;
            }

            set
            {
                deviceId = value;
            }
        }

        public string DeviceDisplayName
        {
            get
            {
                return $"[ {deviceId} ] {deviceName}";
            }
        }

        public Device(string deviceName, int deviceId)
        {
            this.deviceName = deviceName;
            this.deviceId = deviceId;
        }

        public void print()
        {
            Console.WriteLine($"{deviceId}: {deviceName}");
        }
    }
}
