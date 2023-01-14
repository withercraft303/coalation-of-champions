import java.awt.Color;
import java.awt.Graphics;

import Components.*;
import GameAssets.Consts;
import GameAssets.Input;

public class SettingsState extends State{
    private final int GENERAL = 1;
    private final int KEYBINDS = 2;
    private final int scrollfactor = 70;
    int tab = 1;
    int scroll = 0;
    Button generalSettingsButton = new BasicButton(Consts.WINDOWWIDTH/2-450-40, 300, 80, 80);
    Button keybindSettingsButton = new BasicButton(Consts.WINDOWWIDTH/2-450-40, 380, 80, 80);

    ScrollingBasicButton[] generalButtons = {};
    ScrollingBasicButton[] keyBindButtons = {
        new ScrollingBasicButton(Consts.WINDOWWIDTH/2 + 200, 150, 400, 80),
        new ScrollingBasicButton(Consts.WINDOWWIDTH/2 + 200, 250, 400, 80),
        new ScrollingBasicButton(Consts.WINDOWWIDTH/2 + 200, 350, 400, 80),
        new ScrollingBasicButton(Consts.WINDOWWIDTH/2 + 200, 450, 400, 80),
        new ScrollingBasicButton(Consts.WINDOWWIDTH/2 + 200, 550, 400, 80),
        new ScrollingBasicButton(Consts.WINDOWWIDTH/2 + 200, 750, 400, 80),
        new ScrollingBasicButton(Consts.WINDOWWIDTH/2 + 200, 950, 400, 80),
        new ScrollingBasicButton(Consts.WINDOWWIDTH/2 + 200, 1050, 400, 80),
        new ScrollingBasicButton(Consts.WINDOWWIDTH/2 + 200, 1250, 400, 80),
        new ScrollingBasicButton(Consts.WINDOWWIDTH/2 + 200, 1650, 400, 80)
    };
    ScrollingBasicButton[] currentButtons = generalButtons;

    @Override
    public void start(Object[] args) {
    }

    @Override
    public void run() {
        generalSettingsButton.update(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));
        keybindSettingsButton.update(input.mousePosX(), input.mousePosY(), input.mouseIsDown(Input.LMB));

        if (generalSettingsButton.isReleased()){
            tab = GENERAL;
            currentButtons = generalButtons;
        } else if (keybindSettingsButton.isReleased()){
            tab = KEYBINDS;
            currentButtons = keyBindButtons;
        }

        if (input.scroll() != 0){
            int dist = input.scroll()*scrollfactor;
            if (0 < scroll + dist && scroll + dist < 1200){
                scroll += dist;
            } else if (scroll + dist < 1200) {
                scroll = 0;
            } else {
                scroll = 1200;
            }
            for (ScrollingBasicButton b:currentButtons){
                b.offsets(0, -scroll);
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        generalSettingsButton.draw(g);
        keybindSettingsButton.draw(g);
        g.setColor(Color.WHITE);
        g.fillRect(Consts.WINDOWWIDTH/2-450, 0, 900, Consts.WINDOWHEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(Consts.WINDOWWIDTH/2-450, 0, 900, Consts.WINDOWHEIGHT);

        for (Button b: currentButtons){
            if (-80 < b.y() && b.y() < Consts.WINDOWHEIGHT+80){
                b.draw(g);
            }
        }
    }

    @Override
    public Object[] end() {
        return null;
    }
    
}
