using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace UserWebAPI.Entities
{
    public class UserRole
    {
        public User User { get; set; }
        public Role Role { get; set; }
    }
}
