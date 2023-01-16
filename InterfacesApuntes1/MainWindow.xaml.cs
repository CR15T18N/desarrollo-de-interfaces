using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace InterfacesApuntes1
{
    /// <summary>
    /// Lógica de interacción para MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void Button_Click1(object sender, RoutedEventArgs e)
        {

            System.Diagnostics.Process.Start(@"C:\Users\USUARIO\Downloads\npp.8.4.8.Installer.x64.exe");

        }

        private void Button_Click2(object sender, RoutedEventArgs e)
        {
            this.Close();

        }

        private void Standard_Checked(object sender, RoutedEventArgs e)
        {

        }

        private void Personalizada_Copy_Checked(object sender, RoutedEventArgs e)
        {

        }

        private void TextBox_TextChanged(object sender, TextChangedEventArgs e)
        {

        }

        private void Button_Click3(object sender, RoutedEventArgs e)
        {

            Process.Start("C:\\Users\\USUARIO\\Downloads\\Notepad++Setup.pdf");

        }
    }

}
