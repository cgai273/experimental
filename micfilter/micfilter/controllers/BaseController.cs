using micfilter.entities;
using micfilter.helpers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace micfilter
{
    class BaseController
    {
        private AppContext ctx;

        public BaseController()
        {
            ctx = AppContextProvider.getAppContext();

            if (ctx == null)
            {
                throw new Exception("AppContext needs to be initialized before using.");
            }
        }
    }
}
