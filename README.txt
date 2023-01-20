COSC2657 - Android Development 2022C

=== Team 22 ===
Hua Nam Huy  - s3881103
Vu Viet Minh - s3790708
Tran Hoang Vu - s3915185
Nguyen Giang Huy - s3836454

=== TASKS ===
Frontend:
* App UI Concept and Design -> Nam Huy + Giang Huy
* Design and display info for Buyer Main Activity, Product List, Categories, Favorites, ..
.. Seller Main Activity, Fragments, Utils, Adapters -> Giang Huy

* Design and display info for Login, Register, Verification, Product Details, Buyer Shopping Cart,..
.. Buyer Order, Seller Order, Display Order Content, Checkout, App Cover, Adapters -> Nam Huy

Backend: Hoang Vu + Viet Minh
* Setup Rest API and Amazon Web Service database, add queries to get and upload data to database, ..
.. HTTp Classes, Add to Cart, Cart Logic, Order Logic -> Viet Minh
* Implement Login, Logout, Register, Verification, Favorites Logic, Categories Logic, Quality Control
 -> Hoang Vu


=== App Features ===
- 2 types of user accounts: buyer and seller

Buyer:
- Allow buyer to register new accounts, receive code through email and verify their account
- Display up to 4 categories with different products, functional product details display for buyer and seller
- User can add product to favorites lists, add products to shopping cart and place order, search and sort products
- Edit product quantity in shopping cart
- View Order Status

Seller:
- View product lists
- Sellers can add their new products to public product list
- Seller can add images for new products and input description for each product
- Seller can confirm order status and push order

Known bugs and Missing Features
- Lag sometimes cause app to load slowly
- Need to switch to another fragment sometimes to reload the product list and display new products
- Images sometimes go missing in product details
- Product list sometimes loops causing duplicate products to exist (fixed)
- Forgot Password feature not yet implemented
- Seller cannot edit product details
- Users cannot chat with each other
- Missing proper payment feature that would allow user to select payment type
- Unable to design and implement proper sizes and colors UI for product details

Github: https://github.com/MoryDog/E-Commerce_App
Database link: https://github.com/minhboro197/ecommerapp



