
public class Test{

    public static void main(String[] args){
        Picture pic = new Picture("./MyScreenshots/red.png");
        for(int x = 0; x<25; x++){
            for(int y=0;y<25;y++){
                Pixel pix = new Pixel(pic, x, y);
                ProtPixel ppix = new ProtPixel(pix);
                System.out.println(pix);
                System.out.println(ppix);
                pix.setColor(ppix.getColor());
                System.out.println(pix);
            }
        }
        pic.reSavePic();
    }
}