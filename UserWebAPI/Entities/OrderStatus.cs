using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Identity;

namespace UserWebAPI.Entities
{
    public class OrderStatus
    {
        public int Id { get; set; }
        public string UserId { get; set; }
        public int MessageId { get; set; }
    }
}
