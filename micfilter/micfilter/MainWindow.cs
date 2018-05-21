using micfilter.entities;
using micfilter.helpers;
using NAudio.Wave;
using NAudio.Wave.SampleProviders;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace micfilter
{
    public partial class MainWindow : Form
    {
        private AppContext appContext;

        public MainWindow()
        {
            InitializeComponent();
            appContext = AppContextProvider.getAppContext();
        }

        private void displayDevices<T>(ComboBox c, List<T> devices)
        {
            foreach(var dvc in devices)
            {
                c.Items.Add(dvc);
            }
            c.ValueMember = "DeviceId";
            c.DisplayMember = "DeviceDisplayName";
        }

        private void MainWindow_Load(object sender, EventArgs e)
        {
            appContext.loadAllDevices();
            displayDevices<InputDevice>(inputDeviceList, appContext.getInputDevices());
            displayDevices<OutputDevice>(outputDeviceList, appContext.getOutputDevices());
        }

        private void testoutput_Click(object sender, EventArgs e)
        {
            var sine5Seconds = new SignalGenerator() { Gain = 0.2, Frequency = 500 }.Take(TimeSpan.FromSeconds(5));

            using (var wo = new WaveOutEvent())
            {
                Console.WriteLine("Play sound");
                wo.DeviceNumber = 0;
                wo.Init(sine5Seconds);
                wo.Play();
                while (wo.PlaybackState == PlaybackState.Playing)
                {
                    Thread.Sleep(500);
                }
            }
        }

        // TODO: Fix this function.
        private void mictest_Click(object sender, EventArgs e)
        {
            // no op is not output device is set.
            if (appContext.CurrentOutputDevice == null) return;

            var micWaveIn = new WaveIn(this.Handle);
            micWaveIn.BufferMilliseconds = 25;
            //micWaveIn.RecordingStopped += waveIn_RecordingStopped;
            //micWaveIn.DataAvailable += waveIn_DataAvailable;
            // mic.DeviceNumber = 
            using (var wo = new WaveOutEvent())
            {
                Console.WriteLine("Play sound");
                wo.DeviceNumber = appContext.CurrentOutputDevice.DeviceId;
                //wo.Init();
                wo.Play();
                while (wo.PlaybackState == PlaybackState.Playing)
                {
                    Thread.Sleep(500);
                }
            }

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void outputsourcelabel_Click(object sender, EventArgs e)
        {

        }

        private void startBtn_Click(object sender, EventArgs e)
        {

        }

        private void inputDeviceList_SelectedValueChanged(object sender, EventArgs e)
        {
            if (inputDeviceList.SelectedItem != null)
            {
                InputDevice selectedInputDevice = (InputDevice)inputDeviceList.SelectedItem;
                Debug.Print("Selected %s as input device", selectedInputDevice.DeviceDisplayName);
                appContext.setCurrentInputDevice(selectedInputDevice, this.Handle);
            }
        }

        private void outputDeviceList_SelectedValueChanged(object sender, EventArgs e)
        {
            if (outputDeviceList.SelectedItem != null)
            {
                OutputDevice selectedOutputDebice = (OutputDevice)outputDeviceList.SelectedItem;
                Debug.Print("Selected %s as output device", selectedOutputDebice.DeviceDisplayName);
                appContext.setCurrentOutputDevice(selectedOutputDebice);
            }
        }
    }
}
