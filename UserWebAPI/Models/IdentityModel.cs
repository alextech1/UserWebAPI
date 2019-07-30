using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace UserWebAPI.Models
{
    public class ApplicationUser : IdentityUser
    {
        public string FirstName { get; set; }
        public string LastName { get; set; }
       

    }

    public class DataContext : IdentityDbContext<ApplicationUser> //DataContext instead of ApplicationDbContext
    {
        public DataContext(DbContextOptions<DataContext> options)
            : base(options)
        {

        }

        protected override void OnModelCreating(ModelBuilder builder)
        {
            base.OnModelCreating(builder);

            //AspNetUsers -> User
            builder.Entity<ApplicationUser>()
                .ToTable("User");
            //AspNetRoles -> Role
            builder.Entity<IdentityRole>()
                .ToTable("Role");
            //AspNetRoles -> UserRole
            builder.Entity<IdentityUserRole<string>>()
                .ToTable("UserRole");
            //AspNetUserClaims -> UserClaim
            builder.Entity<IdentityUserClaim<string>>()
                .ToTable("UserClaim");
            //AspNetUserLogins -> UserLogin
            builder.Entity<IdentityUserLogin<string>>()
                .ToTable("UserLogin");
        }
    }
}
