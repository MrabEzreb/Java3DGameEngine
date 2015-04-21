package com.ezrebclan.javagame.java;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LoadingDlls extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6491989630869803433L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadingDlls frame = new LoadingDlls();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoadingDlls() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblCopyingDllFiles = new JLabel("Copying Dll Files");
		lblCopyingDllFiles.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblCopyingDllFiles.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCopyingDllFiles, BorderLayout.CENTER);
	}

}
