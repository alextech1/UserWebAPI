﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace UserWebAPI.Entities
{
    public class Message
    {
        //public string[] registration_ids { get; set; }
        public string to { get; set; }
        public Notification notification { get; set; }
        public object data { get; set; }
    }
}
