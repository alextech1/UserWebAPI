using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.AspNetCore.Identity;
using UserWebAPI.Models;

namespace UserWebAPI.Entities
{
    public class User : IdentityUser<int>
    {
        public string FirstName { get; set; }

        public string LastName { get; set; }
        public string Address { get; set; }
        //public ICollection<Status> OrderStatus { get; set; }
        //public ICollection<UserRole> UserRoles { get; set; }

        [NotMapped]
        public byte[] PasswordSalt { get; set; }
    }
}
