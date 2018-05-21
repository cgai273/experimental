using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace micfilter.events
{
    class Event
    {
        static public string BIND_MIC_AS_INPUT = "bind_mic_as_input";

        private string type;

        

        public Event(string type)
        {
            this.type = type;
        }

        public string Type
        {
            get
            {
                return type;
            }
        }
    }
}
