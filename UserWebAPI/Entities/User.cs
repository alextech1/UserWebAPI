using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.AspNetCore.Identity;
using UserWebAPI.Models;

namespace UserWebAPI.Entities
{
    public class User : IdentityUser
    {
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Address { get; set; }
        public int Role { get; set; }
        public string Token { get; set; }
        [NotMapped]
        public byte[] PasswordSalt { get; set; }
        //public List<Cart> MyCart { get; set; }
    }
}
