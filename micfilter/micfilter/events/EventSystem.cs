using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace micfilter.events
{
    class EventSystem
    {
        Dictionary<string, List<Action<Event>>> callbackTable;

        public EventSystem()
        {
            callbackTable = new Dictionary<string, List<Action<Event>>>();
        }

        public void register(string evtName, Action<Event> callback)
        {
            if (!callbackTable.ContainsKey(evtName))
            {
                callbackTable.Add(evtName, new List<Action<Event>>());
            }

            callbackTable[evtName].Add(callback);
        }

        public void dispatch(Event evt)
        {
            if (callbackTable.ContainsKey(evt.Type))
            {
                foreach(Action<Event> f in callbackTable[evt.Type]) {
                    f(evt);
                }
            }
        }
    }
}
