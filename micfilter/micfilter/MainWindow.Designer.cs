using micfilter.ui;

namespace micfilter
{
    partial class MainWindow
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.inputDeviceList = new System.Windows.Forms.ComboBox();
            this.outputDeviceList = new System.Windows.Forms.ComboBox();
            this.inputDeviceListLabel = new System.Windows.Forms.Label();
            this.outputDeviceListLabel = new System.Windows.Forms.Label();
            this.testoutput = new System.Windows.Forms.Button();
            this.mictest = new System.Windows.Forms.Button();
            this.windowlayout = new System.Windows.Forms.FlowLayoutPanel();
            this.startBtn = new System.Windows.Forms.Button();
            this.windowlayout.SuspendLayout();
            this.SuspendLayout();
            // 
            // inputDeviceList
            // 
            this.inputDeviceList.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.inputDeviceList.FormattingEnabled = true;
            this.inputDeviceList.Location = new System.Drawing.Point(15, 28);
            this.inputDeviceList.Name = "inputDeviceList";
            this.inputDeviceList.Size = new System.Drawing.Size(260, 21);
            this.inputDeviceList.TabIndex = 0;
            this.inputDeviceList.SelectedValueChanged += new System.EventHandler(this.inputDeviceList_SelectedValueChanged);
            // 
            // outputDeviceList
            // 
            this.outputDeviceList.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.outputDeviceList.FormattingEnabled = true;
            this.outputDeviceList.Location = new System.Drawing.Point(15, 68);
            this.outputDeviceList.Name = "outputDeviceList";
            this.outputDeviceList.Size = new System.Drawing.Size(260, 21);
            this.outputDeviceList.TabIndex = 1;
            this.outputDeviceList.SelectedValueChanged += new System.EventHandler(this.outputDeviceList_SelectedValueChanged);
            // 
            // inputDeviceListLabel
            // 
            this.inputDeviceListLabel.AutoSize = true;
            this.inputDeviceListLabel.Location = new System.Drawing.Point(15, 12);
            this.inputDeviceListLabel.Name = "inputDeviceListLabel";
            this.inputDeviceListLabel.Size = new System.Drawing.Size(76, 13);
            this.inputDeviceListLabel.TabIndex = 2;
            this.inputDeviceListLabel.Text = "Input Devices:";
            this.inputDeviceListLabel.Click += new System.EventHandler(this.label1_Click);
            // 
            // outputDeviceListLabel
            // 
            this.outputDeviceListLabel.AutoSize = true;
            this.outputDeviceListLabel.Location = new System.Drawing.Point(15, 52);
            this.outputDeviceListLabel.Name = "outputDeviceListLabel";
            this.outputDeviceListLabel.Size = new System.Drawing.Size(84, 13);
            this.outputDeviceListLabel.TabIndex = 3;
            this.outputDeviceListLabel.Text = "Output Devices:";
            this.outputDeviceListLabel.Click += new System.EventHandler(this.outputsourcelabel_Click);
            // 
            // testoutput
            // 
            this.testoutput.Location = new System.Drawing.Point(12, 226);
            this.testoutput.Name = "testoutput";
            this.testoutput.Size = new System.Drawing.Size(75, 23);
            this.testoutput.TabIndex = 4;
            this.testoutput.Text = "Test Output";
            this.testoutput.UseVisualStyleBackColor = true;
            this.testoutput.Click += new System.EventHandler(this.testoutput_Click);
            // 
            // mictest
            // 
            this.mictest.Location = new System.Drawing.Point(105, 226);
            this.mictest.Name = "mictest";
            this.mictest.Size = new System.Drawing.Size(75, 23);
            this.mictest.TabIndex = 5;
            this.mictest.Text = "Mic Test";
            this.mictest.UseVisualStyleBackColor = true;
            this.mictest.Click += new System.EventHandler(this.mictest_Click);
            // 
            // windowlayout
            // 
            this.windowlayout.Controls.Add(this.inputDeviceListLabel);
            this.windowlayout.Controls.Add(this.inputDeviceList);
            this.windowlayout.Controls.Add(this.outputDeviceListLabel);
            this.windowlayout.Controls.Add(this.outputDeviceList);
            this.windowlayout.Location = new System.Drawing.Point(12, 12);
            this.windowlayout.Name = "windowlayout";
            this.windowlayout.Padding = new System.Windows.Forms.Padding(12);
            this.windowlayout.Size = new System.Drawing.Size(292, 127);
            this.windowlayout.TabIndex = 6;
            // 
            // startBtn
            // 
            this.startBtn.Location = new System.Drawing.Point(195, 204);
            this.startBtn.Name = "startBtn";
            this.startBtn.Size = new System.Drawing.Size(109, 45);
            this.startBtn.TabIndex = 7;
            this.startBtn.Text = "Start Recording";
            this.startBtn.UseVisualStyleBackColor = true;
            this.startBtn.Click += new System.EventHandler(this.startBtn_Click);
            // 
            // MainWindow
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(471, 455);
            this.Controls.Add(this.startBtn);
            this.Controls.Add(this.windowlayout);
            this.Controls.Add(this.mictest);
            this.Controls.Add(this.testoutput);
            this.Name = "MainWindow";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.MainWindow_Load);
            this.windowlayout.ResumeLayout(false);
            this.windowlayout.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ComboBox inputDeviceList;
        private System.Windows.Forms.ComboBox outputDeviceList;
        private System.Windows.Forms.Label inputDeviceListLabel;
        private System.Windows.Forms.Label outputDeviceListLabel;
        private System.Windows.Forms.Button testoutput;
        private System.Windows.Forms.Button mictest;
        private System.Windows.Forms.FlowLayoutPanel windowlayout;
        private System.Windows.Forms.Button startBtn;
    }
}

