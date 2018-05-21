using micfilter.entities;
using NAudio.Wave;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace micfilter.helpers
{
    class DeviceHelper
    {
        public static List<OutputDevice> getAvailableOutputDevices()
        {
            List<OutputDevice> list = new List<OutputDevice>();
            for (int n = -1; n < WaveOut.DeviceCount; n++)
            {
                var caps = WaveOut.GetCapabilities(n);
                list.Add(new OutputDevice(caps.ProductName, n));
            }
            return list;
        }

        public static List<InputDevice> getAvailableInputDevices()
        {
            List<InputDevice> list = new List<InputDevice>();
            for (int n = -1; n < WaveIn.DeviceCount; n++)
            {
                var caps = WaveIn.GetCapabilities(n);
                list.Add(new InputDevice(caps.ProductName, n));
            }

            return list;
        }

        public static void logAvailableOutputDevices()
        {
            foreach(var src in getAvailableOutputDevices())
            {
                src.print();
            }
        }

        public static void logAvailableInputDevices()
        {
            foreach (var src in getAvailableInputDevices())
            {
                src.print();
            }
        }
    }
}
