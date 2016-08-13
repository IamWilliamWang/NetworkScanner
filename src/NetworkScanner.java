import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JSpinner;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.awt.Color;

import javax.swing.JTextArea;
import javax.swing.JTextPane;

import java.awt.Toolkit;
import java.awt.Window.Type;

public class NetworkScanner {

	private JFrame frame;
	private JTextField inputTextField;
	private String printString;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NetworkScanner window = new NetworkScanner();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NetworkScanner() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame
				.setIconImage(Toolkit
						.getDefaultToolkit()
						.getImage(
								NetworkScanner.class
										.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		frame.setTitle("Website Thief（获得网页源代码） v1.01");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.PINK);
		frame.setBounds(100, 100, 647, 463);
		frame.getContentPane().setLayout(null);

		inputTextField = new JTextField();
		inputTextField.setText("http://www.baidu.com");
		inputTextField.setFont(new Font("Microsoft JhengHei UI Light",
				Font.PLAIN, 14));
		inputTextField.setBounds(114, 12, 326, 21);
		frame.getContentPane().add(inputTextField);
		inputTextField.setColumns(10);

		JLabel tipLabel = new JLabel("请输入网址：");
		tipLabel.setFont(new Font("仿宋", Font.PLAIN, 18));
		tipLabel.setBounds(10, 10, 123, 22);
		frame.getContentPane().add(tipLabel);

		JButton button = new JButton("确定");
		final JTextArea printTextArea = new JTextArea();
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				printTextArea.setText("请求超时。");
				printString = "";
				String address = inputTextField.getText();
				if (address.substring(0, 4).equals("http") != true) {
					address = "http://" + address;
				}
				try {
					URL u = new URL(address);
					URLConnection uc = u.openConnection();
					InputStream is = uc.getInputStream();
					int c;
					
					while ((c = is.read()) != -1) {
						printString = printString + (char) c;
						if (">};]".indexOf(c)!=-1)
							printString = printString + '\n';

					}
					printTextArea.setText(printString);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.gc();
			}
		});
		button.setBounds(548, 12, 83, 23);
		frame.getContentPane().add(button);
		JScrollPane jsp = new JScrollPane();
		jsp.setBackground(Color.PINK);

		jsp.setBounds(10, 42, 621, 382);
		frame.getContentPane().add(jsp);
		
				
				jsp.setViewportView(printTextArea);
				printTextArea.setEditable(false);
				printTextArea.setText("若出现乱码请重新按下确定键，正常情况会有几秒的停顿（取决于网络情况）");
				printTextArea.setBackground(Color.PINK);
				
				JButton clearButton = new JButton("网址清空");
				clearButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						inputTextField.setText("");
					}
				});
				clearButton.setBounds(450, 12, 88, 23);
				frame.getContentPane().add(clearButton);
	}
}
