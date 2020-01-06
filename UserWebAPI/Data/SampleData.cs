using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using System;
using System.Linq;
using UserWebAPI.Entities;
using UserWebAPI.Models;

public class YourDbContextSeedData
{
    private DataContext _context;

    public YourDbContextSeedData(DataContext context)
    {
        _context = context;
    }

    public async void SeedProducts()
    {
        var product1 = new Product
        {
            Name = "Christmas Book",
            Photo = "pizzapic1.jpg",
            Price = 5
        };

        if (!_context.Products.Any(u => u.Name == product1.Name))
        {
            _context.Products.Add(product1);
        }

        var product2 = new Product
        {
            Name = "NewYear Book",
            Photo = "pizzapic2.jpg",
            Price = 4
        };


        if (!_context.Products.Any(u => u.Name == product2.Name))
        {
            _context.Products.Add(product2);
        }

        var product3 = new Product
        {
            Name = "T-Shirt",
            Photo = "pizzapic3.jpg",
            Price = 15
        };

        if (!_context.Products.Any(u => u.Name == product3.Name))
        {
            _context.Products.Add(product3);
        }

        //await _context.SaveChangesAsync();
        _context.SaveChanges();
        //_context.Dispose();
    }

    public async void SeedAdminUser()
    {
        var user = new User
        {
            UserName = "admin",
            NormalizedUserName = "ADMIN",
            Email = "admin@email.com",
            NormalizedEmail = "email@email.com",
            EmailConfirmed = true,
            Role = 0,
            LockoutEnabled = false,
            SecurityStamp = Guid.NewGuid().ToString()
        };

        if (!_context.Users.Any(u => u.UserName == user.UserName))
        {
            var password = new PasswordHasher<User>();
            var hashed = password.HashPassword(user, "password");
            user.PasswordHash = hashed;
            _context.Users.Add(user);
            //await userStore.AddToRoleAsync(user, "admin");
        }

        //await _context.SaveChangesAsync();
        _context.SaveChanges();
        //_context.Dispose();
    }
}