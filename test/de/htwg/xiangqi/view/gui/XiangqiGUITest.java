package de.htwg.xiangqi.view.gui;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.xiangqi.XiangqiGameModule;
import de.htwg.xiangqi.controller.IBoardManager;

public class XiangqiGUITest {

	IBoardManager bm;
	XiangqiGUI gui;
	
	@Before
	public void setUp() {
		Injector injector = Guice.createInjector(new XiangqiGameModule());
		bm = injector.getInstance(IBoardManager.class);
		bm.setStartBoard();
		gui = injector.getInstance(XiangqiGUI.class);
	}
	
	@Test
	public void testUpdate() {
		assertNull(bm.getMessage());
		bm.inputMove("9 0 8 5");
		gui.update();
		assertNotNull(bm.getMessage());
		bm.inputMove("9 0 8 0");
		gui.update();
		assertNull(bm.getMessage());
		bm.getBoard().getSquareMatrix()[0][4].getPiece().setIsCaptured(true);
		gui.update();
	}
	
//	@After
	public void tearDown() {
		bm.close();
	}

}
