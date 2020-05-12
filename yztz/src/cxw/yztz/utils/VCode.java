package cxw.yztz.utils;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码生成器
 * @author zhenyu
 */
public class VCode{
	
	/**
	 * 验证码来源
	 */
	final private char[] code = {
		'2', '3', '4', '5', '6', '7', '8', '9',
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
		'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 
		'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
		'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R',
		'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
	};
	/**
	 * 字体
	 */
	final private String[] fontNames = new String[]{
			"黑体", "宋体", "Courier", "Arial", 
			"Verdana", "Times", "Tahoma", "Georgia"};
	/**
	 * 字体样式
	 */
	final private int[] fontStyles = new int[]{
			Font.BOLD, Font.ITALIC|Font.BOLD
	};
	
	/**
	 * 验证码长度
	 * 默认4个字符
	 */
	private int vCodeLen = 4;
	/**
	 * 验证码图片字体大小
	 * 默认17
	 */
	private int fontsize = 21;
	/**
	 * 验证码图片宽度
	 */
	private int width = (fontsize+1)* vCodeLen +10;
	/**
	 * 验证码图片高度
	 */
	private int height = fontsize+12;
	/**
	 * 干扰线条数
	 * 默认3条
	 */
	private int disturbLine = 3;
	
	
	public VCode(){}
	
	/**
	 * 指定验证码长度
	 * @param vCodeLen 验证码长度
	 */
	public VCode(int vCodeLen) {
		this.vCodeLen = vCodeLen;
		this.width = (fontsize+1)*vCodeLen+10;
	}

	/**
	 * 获得旋转字体的验证码图片
	 * @param vCode
	 * @param drawLine 是否画干扰线
	 * @return
	 */
	public BufferedImage generatorRotateVCodeImage(String vCode, boolean drawLine){
		//创建验证码图片
		BufferedImage rotateVCodeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = rotateVCodeImage.createGraphics();
		//填充背景色
		g2d.setColor(new Color(246, 240, 250));
		g2d.fillRect(0, 0, width, height);
		if(drawLine){
			drawDisturbLine(g2d);
		}
		//在图片上画验证码
		for(int i = 0;i < vCode.length();i++){
			BufferedImage rotateImage = getRotateImage(vCode.charAt(i));
			g2d.drawImage(rotateImage, null, (int) (this.height * 0.7) * i, 0);
		}
		g2d.dispose();
		return rotateVCodeImage;
	}
	/**
	 * 生成验证码
	 * @return 验证码
	 */
	public String generatorVCode(){
		int len = code.length;
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < vCodeLen; i++){
			int index = ran.nextInt(len);
			sb.append(code[index]);
		}
		return sb.toString();
	}
	/**
	 * 为验证码图片画一些干扰线
	 * @param g 
	 */
	private void drawDisturbLine(Graphics g){
		Random ran = new Random();
		for(int i = 0; i < disturbLine; i++){
			int x1 = ran.nextInt(width);
			int y1 = ran.nextInt(height);
			int x2 = ran.nextInt(width);
			int y2 = ran.nextInt(height);
			g.setColor(getRandomColor());
			//画干扰线
			g.drawLine(x1, y1, x2, y2);
		}
	}
	/**
	 * 获取一张旋转的图片
	 * @param c 要画的字符
	 * @return
	 */
	private BufferedImage getRotateImage(char c){
		BufferedImage rotateImage = new BufferedImage(height, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = rotateImage.createGraphics();
		//设置透明度为0
		g2d.setColor(new Color(255, 255, 255, 0));
		g2d.fillRect(0, 0, height, height);
		Random ran = new Random();
		g2d.setFont(new Font(fontNames[ran.nextInt(fontNames.length)], fontStyles[ran.nextInt(fontStyles.length)], fontsize));
		g2d.setColor(getRandomColor());
		double theta = getTheta();
		//旋转图片
		g2d.rotate(theta, height/2, height/2);
		g2d.drawString(Character.toString(c), (height-fontsize)/2, fontsize+5);
		g2d.dispose();
		
		return rotateImage;
	}
	/**
	 * @return 返回一个随机颜色
	 */
	private Color getRandomColor(){
		Random ran = new Random();
		return new Color(ran.nextInt(220), ran.nextInt(220), ran.nextInt(220)); 
	}
	/**
	 * @return 角度
	 */
	private double getTheta(){
		return ((int) (Math.random()*1000) % 2 == 0 ? -1 : 1)*Math.random();
	}

	/**
	 * @return 验证码字符个数
	 */
	public int getvCodeLen() {
		return vCodeLen;
	}
	/**
	 * 设置验证码字符个数
	 * @param vCodeLen
	 */
	public void setvCodeLen(int vCodeLen) {
		this.width = (fontsize+3)* vCodeLen +10;
		this.vCodeLen = vCodeLen;
	}
	/**
	 * @return 字体大小
	 */
	public int getFontsize() {
		return fontsize;
	}
	/**
	 * 设置字体大小
	 * @param fontsize
	 */
	public void setFontsize(int fontsize) {
		this.width = (fontsize+3)* vCodeLen +10;
		this.height = fontsize+15;
		this.fontsize = fontsize;
	}
	/**
	 * @return 图片宽度
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * 设置图片宽度
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return 图片高度
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * 设置图片高度
	 * @param height 
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * @return 干扰线条数
	 */
	public int getDisturbLine() {
		return disturbLine;
	}
	/**
	 * 设置干扰线条数
	 * @param disturbLine
	 */
	public void setDisturbLine(int disturbLine) {
		this.disturbLine = disturbLine;
	}
	
}

