package org.openmicroscopy.shoola.examples.browser;

// java imports
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPanel;

// third-party libraries

// application-internal dependencies

/**
 * 
 */
class BrowserCanvas extends JPanel 
{
	private static final long serialVersionUID = 7625488987526070516L;
	
	// the images to display
	private List<BufferedImage> images;

	// creates a new instance
	BrowserCanvas() 
	{
		setDoubleBuffered(true);
		setBackground(Color.white);
		Dimension d = new Dimension(300, 500);
		setSize(d);
		setPreferredSize(d);
	}

	/**
	 * Sets the images to display.
	 * 
	 * @param images
	 *            The images to display.
	 */
	void setImages(List<BufferedImage> images) 
	{
		this.images = images;
		repaint();
	}

	/**
	 * Overridden to paint the images.
	 * 
	 * @see javax.swing.JComponent#paintComponent(Graphics)
	 */
	public void paintComponent(Graphics g) 
	{
		if (images == null || images.size() == 0)
			return;
		
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		// layout the images
		g2D.setColor(getBackground());
		g2D.fillRect(0, 0, getWidth(), getHeight());
		Iterator<BufferedImage> i = images.iterator();
		
		int x = 0;
		int y = 0;
		int w = 0;
		int h = 0;
		BufferedImage image;
		int width = getWidth();
		int maxY = 0;
		int gap = 2;
		
		while (i.hasNext()) 
		{
			image = i.next();
			h = image.getHeight();
			w = image.getWidth();
			
			if (maxY < h)
				maxY = h;
			
			if (x != 0) 
			{
				if (x + w > width) 
				{
					x = 0;
					y = y + maxY;
					y = y + gap;
					maxY = 0;
				}
			}
			
			g2D.drawImage(image, x, y, null);
			x += w;
			x += gap;
		}
	}
}
