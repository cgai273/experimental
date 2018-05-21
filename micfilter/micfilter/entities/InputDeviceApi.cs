using NAudio.Wave;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace micfilter.entities
{
    interface InputDeviceApi
    {
        void bindProvider(BufferedWaveProvider provider);
        void initialize(IntPtr parentHandle);
        void dispose();
        void start();
        void pause();
    }
}
