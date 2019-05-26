package my;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame
{
	public MyFrame(String title) {
		super(title);
		JPanel root = new JPanel();
		this.setContentPane(root);
		root.setLayout(new BorderLayout());
		
		PieChart chart = new PieChart();
		root.add(chart, BorderLayout.CENTER);
		
		chart.addPart(70, "A", new Color(0x00CDCD));
		chart.addPart(20, "B", new Color(0xFFC1C1));
		chart.addPart(10, "C", new Color(0xEEE8AA));
		
		chart.setPartClickedListener(new PieChart.PartClickedListener()
		{
			
			@Override
			public void partClicked(Object tag)
			{
				System.out.println("点中了分区：" + tag);
			}
		});
	}

}
