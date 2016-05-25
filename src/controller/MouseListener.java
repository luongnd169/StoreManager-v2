package controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gui.Main;

public class MouseListener extends MouseAdapter {
	Main main = new Main();
	
	@SuppressWarnings("unused")
	@Override
	public void mouseClicked(MouseEvent e) {
		Point point = e.getPoint();
		int col = main.getTableTonKho().columnAtPoint(point);
	}

}
