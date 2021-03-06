package de.htwg.xiangqi.persistence;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

import org.apache.log4j.Logger;

import de.htwg.xiangqi.controller.IBoardManager;
import de.htwg.xiangqi.model.Board;

public class PersistenceLoadDialog extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 400, HEIGHT = 200;
	
	private JButton ok = new JButton("ok"), cancel = new JButton("cancel");
	private JComboBox combobox;
	private IBoardManager bm;
	private Logger logger = Logger.getLogger("de.htwg.xiangqi.persistence");

	public PersistenceLoadDialog(JFrame f, final IBoardManager bm, List<Board> list){
		super(f);
		
		this.bm = bm;
		
		setSize(new Dimension(WIDTH, HEIGHT));
		setTitle("Load Dialog");
		setResizable(true);
		setModal(true);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		List<Board> boardList = new ArrayList<Board>();
		for(Board board : list){
			boardList.add(board);
		}
		Board[] boardArray = new Board[boardList.size()];
		boardArray = boardList.toArray(boardArray);
		combobox = new JComboBox(boardArray);

		ok.addActionListener(this);
		cancel.addActionListener(this);
		
		
		add(combobox);
		add(cancel);
		add(ok);
		
		pack();
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if((JButton)e.getSource() == ok){
			try {
				bm.setBoard(((Board)combobox.getSelectedItem()).clone());
			} catch (CloneNotSupportedException e1) {
				logger.info((String)e1.toString());
			}
			bm.notifyObservers();
			this.dispose();
		}else{
			this.dispose();
		}
	}
}
