package com.cetpa.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cetpa.entities.Product;
import com.cetpa.services.ProductService;

@CrossOrigin
@RestController
@RequestMapping("product-service")
public class ProductRestController {

	@Autowired
	private ProductService productService;
	
//	http://localhost:8080/product-service/add
	@PostMapping("add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product)
	{
		productService.saveProduct(product);
		ResponseEntity<Product> response=ResponseEntity.status(HttpStatus.CREATED).body(product);
		return response;
	}
	
//	http://localhost:8080/product-service/list
	@GetMapping("list")
	public ResponseEntity<List<Product>> getproductList()
	{
		List<Product>productlist=productService.getList();
		return ResponseEntity.status(HttpStatus.OK).body(productlist);
	}
	
//	http://localhost:8080/product-service/details/byId?pid=104
	@GetMapping("details/byId")
	public ResponseEntity<Product> getProductById(@RequestParam("pid") int pid)
	{
		Product product=productService.getDetailsById(pid);
		if(product==null)
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}
	
//	using pathVaraible
//	http://localhost:8080/product-service/details/byId/101
	@GetMapping("details/byId/{pid}")
	public ResponseEntity<Product> getProductById1(@PathVariable("pid") int pid)
	{
		Product product=productService.getDetailsById(pid);
		if(product==null)
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}
	
//	http://localhost:8080/product-service/list/byname/Laptop
	@GetMapping("list/byname/{name}")
	public ResponseEntity<List<Product>> getProductListByName(@PathVariable("name") String name)
	{
		List<Product>productlist=productService.getListByName(name);
		if(productlist.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.OK).body(productlist);
	}
	
//	http://localhost:8080/product-service/list/bybrand/Hp
	@GetMapping("list/bybrand/{brand}")
	public ResponseEntity<List<Product>> getProductListByBrand(@PathVariable("brand") String brand)
	{
		List<Product>productlist=productService.getListByBrand(brand);
		if(productlist.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.OK).body(productlist);
	}
	
//	http://localhost:8080/product-service/list/byprice/lessthan?price=1600
	@GetMapping("list/byprice/lessthan")
	public ResponseEntity<List<Product>> getProductListByPriceLessThan(@RequestParam("price") int price)
	{
		List<Product>productlist=productService.getListByPriceLessThan(price);
		if(productlist.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.OK).body(productlist);
	}
	
//	http://localhost:8080/product-service/list/byprice/greaterthan?price=40000
	@GetMapping("list/byprice/greaterthan")
	public ResponseEntity<List<Product>> getProductListByPriceGreaterThan(@RequestParam("price") int price)
	{
		List<Product>productlist=productService.getListByPriceGreaterThan(price);
		if(productlist.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.OK).body(productlist);
	}
	
//  http://localhost:8080/product-service/list/byprice/between/20000/46000
	@GetMapping("list/byprice/between/{price1}/{price2}")
	public ResponseEntity<List<Product>> getProductListByPriceBetween(@PathVariable("price1") int price1,@PathVariable("price2") int price2)
	{
		List<Product>productlist=productService.getListByPriceBetween(price1,price2);
		if(productlist.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.OK).body(productlist);
	}
	
//  http://localhost:8080/product-service/list/byprice/between?price1=1500&price2=40000
	@GetMapping("list/byprice/between")
	public ResponseEntity<List<Product>> getProductListByPriceBetween1(@RequestParam("price1") int price1,@RequestParam("price2") int price2)
	{
		List<Product>productlist=productService.getListByPriceBetween(price1,price2);
		if(productlist.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.status(HttpStatus.OK).body(productlist);
	}
	
//	http://localhost:8080/product-service/delete/byid?pid=106
	@DeleteMapping("delete/byid")
	public ResponseEntity<Product>deleteById(@RequestParam("pid") int pid)
	{
		Product product=productService.getById(pid);
		if(product==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		productService.deleteById(pid);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}
	
	@PutMapping("update")
	public ResponseEntity<Product> updateResource(@RequestParam("pid") int pid, @RequestBody Product product)
	{
		Product producto=productService.getById(pid);
		//If resource does not exist
		if(producto==null)
		{
			productService.saveProduct(product);
			return ResponseEntity.status(HttpStatus.CREATED).body(product);
		}
		//If resource exists
		product.setPid(pid);
		productService.updateProduct(product);
		return ResponseEntity.ok(product);
	}
	
	@PatchMapping("pupdate")
	public ResponseEntity<Product> pupdateResource(@RequestParam int pid,@RequestBody Product product)
	{
		Product producto=productService.getById(pid);
		if(producto==null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		String name=product.getName();
		String brand=product.getBrand();
		int price=product.getPrice();
		if(name!=null)
			producto.setName(name);
		if(brand!=null)
			producto.setBrand(brand);
		if(price!=0)
			producto.setPrice(price);
		productService.updateProduct(producto);
		return ResponseEntity.ok(producto);
	}
}
