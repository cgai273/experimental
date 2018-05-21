using NAudio.Wave;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace micfilter.entities
{
    class InputDevice : Device, InputDeviceApi
    {
        private BufferedWaveProvider provider;
        private WaveIn waveIn;

        public InputDevice(string deviceName, int deviceId) : base(deviceName, deviceId)
        {
  
        }

        public void initialize(IntPtr controlHandle)
        {
            waveIn = new WaveIn();
            waveIn.BufferMilliseconds = 25;
            waveIn.RecordingStopped += onRecordingStopped;
            waveIn.DataAvailable += onDataAvailable;
        }

        public void configure()
        {
            
        }

        public void bindProvider(BufferedWaveProvider provider)
        {
            this.provider = provider;
        }

        public void dispose()
        {
            if (waveIn != null)
            {
                waveIn.Dispose();
                waveIn = null;
            }
        }

        public void start()
        {
            if (waveIn != null)
            {
                waveIn.StartRecording();
            }
        }

        public void pause()
        {
            if (waveIn != null)
            {
                waveIn.StopRecording();
            }
        }

        private void onDataAvailable(object sender, WaveInEventArgs e)
        {
            if (provider != null)
            {
                provider.AddSamples(e.Buffer, 0, e.BytesRecorded);
            }
        }

        private void onRecordingStopped(object sender, StoppedEventArgs e)
        {

        }
    }
}
