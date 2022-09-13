namespace lab2
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
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
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.openFileDialog = new System.Windows.Forms.OpenFileDialog();
            this.btnOpen = new System.Windows.Forms.Button();
            this.checkBoxHidden = new System.Windows.Forms.CheckBox();
            this.checkBoxHash = new System.Windows.Forms.CheckBox();
            this.checkBoxArch = new System.Windows.Forms.CheckBox();
            this.label1 = new System.Windows.Forms.Label();
            this.labelHash = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // openFileDialog
            // 
            this.openFileDialog.FileName = "openFileDialog";
            // 
            // btnOpen
            // 
            this.btnOpen.Location = new System.Drawing.Point(407, 196);
            this.btnOpen.Name = "btnOpen";
            this.btnOpen.Size = new System.Drawing.Size(153, 51);
            this.btnOpen.TabIndex = 0;
            this.btnOpen.Text = "Open file";
            this.btnOpen.UseVisualStyleBackColor = true;
            this.btnOpen.Click += new System.EventHandler(this.BtnOpen_Click);
            // 
            // checkBoxHidden
            // 
            this.checkBoxHidden.AutoSize = true;
            this.checkBoxHidden.Location = new System.Drawing.Point(174, 270);
            this.checkBoxHidden.Name = "checkBoxHidden";
            this.checkBoxHidden.Size = new System.Drawing.Size(77, 24);
            this.checkBoxHidden.TabIndex = 1;
            this.checkBoxHidden.Text = "hidden";
            this.checkBoxHidden.UseVisualStyleBackColor = true;
            // 
            // checkBoxHash
            // 
            this.checkBoxHash.AutoSize = true;
            this.checkBoxHash.Location = new System.Drawing.Point(174, 210);
            this.checkBoxHash.Name = "checkBoxHash";
            this.checkBoxHash.Size = new System.Drawing.Size(82, 24);
            this.checkBoxHash.TabIndex = 2;
            this.checkBoxHash.Text = "hashing";
            this.checkBoxHash.UseVisualStyleBackColor = true;
            // 
            // checkBoxArch
            // 
            this.checkBoxArch.AutoSize = true;
            this.checkBoxArch.Location = new System.Drawing.Point(174, 149);
            this.checkBoxArch.Name = "checkBoxArch";
            this.checkBoxArch.Size = new System.Drawing.Size(91, 24);
            this.checkBoxArch.TabIndex = 3;
            this.checkBoxArch.Text = "archiving";
            this.checkBoxArch.UseVisualStyleBackColor = true;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 421);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(69, 20);
            this.label1.TabIndex = 4;
            this.label1.Text = "File hash:";
            // 
            // labelHash
            // 
            this.labelHash.AutoSize = true;
            this.labelHash.Location = new System.Drawing.Point(87, 421);
            this.labelHash.Name = "labelHash";
            this.labelHash.Size = new System.Drawing.Size(0, 20);
            this.labelHash.TabIndex = 5;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.labelHash);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.checkBoxArch);
            this.Controls.Add(this.checkBoxHash);
            this.Controls.Add(this.checkBoxHidden);
            this.Controls.Add(this.btnOpen);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private OpenFileDialog openFileDialog;
        private Button btnOpen;
        private CheckBox checkBoxHidden;
        private CheckBox checkBoxHash;
        private CheckBox checkBoxArch;
        private Label label1;
        private Label labelHash;
    }
}