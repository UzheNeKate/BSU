using System.ComponentModel;
using System.Diagnostics;
using System.IO.Compression;
using System.Security.Cryptography;

namespace lab2
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            //Сначала нужно собрать решение (перед запуском)
        }

        private void BtnOpen_Click(object sender, EventArgs e)
        {
            using var openFileDialog = new OpenFileDialog();
            openFileDialog.RestoreDirectory = true;
            openFileDialog.Title = "Choose file";
            openFileDialog.CheckFileExists = true;

            if (openFileDialog.ShowDialog() == DialogResult.OK)
            {
                var filePath = openFileDialog.FileName;
                var fileInfo = new FileInfo(filePath);

                try
                {
                    if (checkBoxArch.Checked)
                    {
                        using var sourceStream = new FileStream(filePath, FileMode.OpenOrCreate);
                        using var targetStream = File.Create(@$"{fileInfo.DirectoryName}\{fileInfo.Name}.zip");
                        using var compressionStream = new GZipStream(targetStream, CompressionMode.Compress);
                        sourceStream.CopyTo(compressionStream);
                    }
                }
                catch (UnauthorizedAccessException ex)
                {
                    const int ERROR_CANCELLED = 1223; //The operation was canceled by the user.

                    var info = new ProcessStartInfo(Path.GetFullPath(
                        @"C:\Учеба\3 курс\6 сем\mobile security\lab2\Archive\bin\Debug\net6.0\Archive.exe"));
                    info.UseShellExecute = true;
                    info.ArgumentList.Add(filePath);
                    info.Verb = "runas";
                    try
                    {
                        Process.Start(info);
                    }
                    catch (Win32Exception w)
                    {
                        if (w.NativeErrorCode == ERROR_CANCELLED)
                            MessageBox.Show("Why you no select Yes?");
                    }
                    catch (UnauthorizedAccessException exc)
                    {
                        MessageBox.Show("Still does not work\n" + exc.Message);
                    }
                }

                try
                {
                    if (checkBoxHash.Checked)
                    {
                        using var md5 = MD5.Create();
                        using var stream = File.OpenRead(filePath);
                        labelHash.Text = System.Text.Encoding.UTF8.GetString(md5.ComputeHash(stream));
                    }
                }
                catch (UnauthorizedAccessException ex)
                {
                    const int ERROR_CANCELLED = 1223; //The operation was canceled by the user.

                    try
                    {
                        var pr = new Process();
                        pr.StartInfo.FileName = Path.GetFullPath(
                        @"C:\Учеба\3 курс\6 сем\mobile security\lab2\Hash\bin\Debug\net6.0\Hash.exe");
                        pr.StartInfo.UseShellExecute = true;
                        pr.StartInfo.ArgumentList.Add(filePath);
                        pr.StartInfo.Verb = "runas";
                        pr.Start();
                    }
                    catch (Win32Exception w)
                    {
                        if (w.NativeErrorCode == ERROR_CANCELLED)
                            MessageBox.Show("Why you no select Yes?");
                    }
                    catch (UnauthorizedAccessException exc)
                    {
                        MessageBox.Show("Still does not work\n" + exc.Message);
                    }
                }

                try
                {
                    if (checkBoxHidden.Checked)
                    {
                        File.SetAttributes(filePath, File.GetAttributes(filePath) | FileAttributes.Hidden);
                    }
                }
                catch (UnauthorizedAccessException ex)
                {
                    const int ERROR_CANCELLED = 1223; //The operation was canceled by the user.

                    var info = new ProcessStartInfo(Path.GetFullPath(
                        @"C:\Учеба\3 курс\6 сем\mobile security\lab2\Hidden\bin\Debug\net6.0\Hidden.exe"));
                    info.UseShellExecute = true;
                    info.ArgumentList.Add(filePath);
                    info.Verb = "runas";
                    try
                    {
                        Process.Start(info);
                    }
                    catch (Win32Exception w)
                    {
                        if (w.NativeErrorCode == ERROR_CANCELLED)
                            MessageBox.Show("Why you no select Yes?");
                    }
                    catch (UnauthorizedAccessException exc)
                    {
                        MessageBox.Show("Still does not work\n" + exc.Message);
                    }
                }
            }
        }
    }
}