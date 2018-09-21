package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

// Example scenarios for testing
//   Item("+5 Dexterity Vest", 10, 20));
//   Item("Aged Brie", 2, 0));
//   Item("Elixir of the Mongoose", 5, 7));
//   Item("Sulfuras, Hand of Ragnaros", 0, 80));
//   Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
//   Item("Conjured Mana Cake", 3, 6));

	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_10_11() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(11, itemBrie.getQuality());
	}
    
	@Test
	public void testUpdateEndOfDay_AgedBrieReallyOld() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 10) );
		
		// Act
		for(int i = 0; i < 60; i++){
		    store.updateEndOfDay();
		}
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(50, itemBrie.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_CreateSulfurasWithFalseQuality() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert. This assert would pass if there would be prevention of falsely created items.
		List<Item> items = store.getItems();
		Item itemSulfuras = items.get(0);
		assertEquals("Created Sulfuras with false quality and run updateEndOfDay", 80, itemSulfuras.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_SulfurasGettingOlder() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 2, 80) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemSulfuras = items.get(0);
		assertEquals(80, itemSulfuras.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_PassQuality() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 12, 0) );
		
		// Acts and Asserts
		store.updateEndOfDay();
		List<Item> items = store.getItems();
		Item itemPass = items.get(0);
		assertEquals(1, itemPass.getQuality());
		store.updateEndOfDay();
		assertEquals(2, itemPass.getQuality());
		store.updateEndOfDay();
		assertEquals(4, itemPass.getQuality());
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		assertEquals(15, itemPass.getQuality());
		store.updateEndOfDay();
		assertEquals(18, itemPass.getQuality());
		store.updateEndOfDay();
		store.updateEndOfDay();
		store.updateEndOfDay();
		assertEquals(27, itemPass.getQuality());
		store.updateEndOfDay();
		assertEquals(0, itemPass.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_RandomItemQuality() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Just some item", 2, 10) );
		
		// Acts and Asserts
		store.updateEndOfDay();
		List<Item> items = store.getItems();
		Item itemSome = items.get(0);
		assertEquals(9, itemSome.getQuality());
		store.updateEndOfDay();
		assertEquals(8, itemSome.getQuality());
		store.updateEndOfDay();
		assertEquals(6, itemSome.getQuality());
		store.updateEndOfDay();
		assertEquals(4, itemSome.getQuality());
		store.updateEndOfDay();
		store.updateEndOfDay();
		assertEquals(0, itemSome.getQuality());
		store.updateEndOfDay();
		assertEquals(0, itemSome.getQuality());
	}
	
	@Test
	public void testUpdateEndOfDay_QualityOver50() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 10) );
		
		// Acts and Asserts
		store.updateEndOfDay();
		List<Item> items = store.getItems();
		Item itemSome = items.get(0);
		assertEquals(9, itemSome.getQuality());
		store.updateEndOfDay();
		assertEquals(8, itemSome.getQuality());
		store.updateEndOfDay();
		assertEquals(6, itemSome.getQuality());
		store.updateEndOfDay();
		assertEquals(4, itemSome.getQuality());
		store.updateEndOfDay();
		store.updateEndOfDay();
		assertEquals(0, itemSome.getQuality());
		store.updateEndOfDay();
		assertEquals(0, itemSome.getQuality());
	}
}
