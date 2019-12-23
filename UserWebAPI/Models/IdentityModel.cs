using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using UserWebAPI.Entities;

namespace UserWebAPI.Models
{
    //IdentityDbContext<User, Role, int, IdentityUserClaim<int>, UserRole, IdentityUserLogin<int>,
    //    IdentityRoleClaim<int>, IdentityUserToken<int>>
    public class DataContext : DbContext
    {
        public DataContext(DbContextOptions<DataContext> options) : base(options) { }

        public DbSet<User> Users { get; set; }
        public  DbSet<OrderStatus> OrderStatus { get; set; }
        protected override void OnModelCreating(ModelBuilder builder)
        {
            base.OnModelCreating(builder);

            builder.Entity<User>(u =>
            {
                u.ToTable("User");
                u.HasKey(x => x.Id);
            });
            builder.Entity<Role>(u =>
            {
                u.ToTable("Role");
                u.HasKey(x => x.Id);
            });

            builder.Entity<OrderStatus>(u =>
            {
                u.ToTable("OrderStatus");
                u.HasKey(x => x.Id);
            });

            builder.Entity<Cart>(u =>
            {
                u.ToTable("Cart");
                u.HasKey(x => x.Id);
            });

            builder.Entity<IdentityUserRole<string>>(u =>
            {
                u.ToTable("UserRole");
                u.HasKey(x => new { x.RoleId, x.UserId });
            });
            builder.Entity<IdentityUserClaim<string>>(u =>
            {
                u.ToTable("UserClaim");
                u.HasKey(x => x.Id);
            });
            builder.Entity<IdentityUserLogin<string>>(u =>
            {
                u.ToTable("UserLogin");
                u.HasKey(x => new { x.ProviderKey, x.LoginProvider });
            });
          
        }
        public DbSet<UserWebAPI.Entities.Cart> Cart { get; set; }
    }
}
