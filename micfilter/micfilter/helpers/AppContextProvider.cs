using micfilter.entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace micfilter.helpers
{
    class AppContextProvider
    {
        private static AppContext context;
        
        public static void initAppContext()
        {
            if (context == null)
            {
                context = new AppContext();
            }
        }

        public static void setAppContext(AppContext newContext) 
        {
            context = newContext;
        }

        public static AppContext getAppContext()
        {
            return context;
        }
    }
}
