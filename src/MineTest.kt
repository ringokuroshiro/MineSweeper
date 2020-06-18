import org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MineTest {
	
	val mine = Mine(9,9);
	
	@Test
	fun test1() {
		mine.setMine(0, 0);
		assertEquals(true, mine.getMine(0,0));
	}
	
	@Test
	fun test2() {
		mine.setMine(2, 3);
		assertEquals(true, mine.getMine(2,3));
	}
	
	@Test
	fun test3() {
		mine.setMine(8, 8);
		assertEquals(true, mine.getMine(8,8));
	} 
	
	@Test
	fun test4() {
		mine.setMine(8, 8);
		mine.setMine(7, 7);
		assertEquals(2, mine.countMine(8,7));
	}
	
	@Test
	fun test5() {
		mine.setMine(0, 0);
		assertEquals(1, mine.countMine(1, 1));
	}
}
