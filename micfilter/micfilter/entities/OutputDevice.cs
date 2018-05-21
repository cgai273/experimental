using NAudio.Wave;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace micfilter.entities
{
    class OutputDevice : Device, OutputDeviceApi
    {
        private BufferedWaveProvider provider;
        private WaveOut waveOut;

        public OutputDevice(string deviceName, int deviceId) : base(deviceName, deviceId)
        {

            
        }

        public void initialize(IntPtr parentHandle)
        {
            waveOut = new WaveOut();
            waveOut.DesiredLatency = 100;
            waveOut.PlaybackStopped += onPlaybackStopped;
        }

        public void bindProvider(BufferedWaveProvider provider)
        {
            this.provider = provider;
            waveOut.Init(provider);
        }

        public void start()
        {
            waveOut.Play();
        }

        public void pause()
        {
            waveOut.Pause();
        }

        public void dispose()
        {

        }

        private void onPlaybackStopped(object sender, StoppedEventArgs e)
        {

        }
    }
}
