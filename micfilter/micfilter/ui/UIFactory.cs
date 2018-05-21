using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace micfilter.ui
{
    class UIFactory
    {
        public static ComboBox getDropdown(string name)
        {
            ComboBox dropdown = new ComboBox();
            dropdown.Name = name;
            dropdown.DropDownStyle = ComboBoxStyle.DropDownList;
            dropdown.FormattingEnabled = true;

            return dropdown;
        }
    }
}
