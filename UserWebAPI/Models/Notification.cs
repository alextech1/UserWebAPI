using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace UserWebAPI.Models
{
    public class Notification
    {
        public string title { get; set; }
        public string body { get; set; }
        public bool mutable_content { get; set; }
        public string sound { get; set; }
    }
}
