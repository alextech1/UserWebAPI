using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace UserWebAPI.Models
{
    public class AddCartModel
    {
        public int cartId { get; set; }
        public int userId { get; set; }
        public int productId { get; set; }
        public int quantity { get; set; }
    }
}
