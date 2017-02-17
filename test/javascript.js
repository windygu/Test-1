var lang = new JavaImporter();
lang.importPackage(java.lang);
lang.System.out.println("Hello");

var swing = new JavaImporter();
swing.importPackage(javax.swing);
with(swing)
{
	var frame = new JFame("Swing Application");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	var button = new JButton("Hello");
	button.addActionListener(function()
			{
				lang.System.out.println("Text from button");
			});
	frame.add(button);
	frame.pack();
	frame.setResizable(false);
	frame.setVisible(true);
}