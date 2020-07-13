package com.company;

public class Pusher extends Thread {
    BlockArrayStack stack;
    int i = 0;
    int whoAmI = 0;

    public Pusher(int whoAmI) {
        this.whoAmI = whoAmI;
        stack = new BlockArrayStack();
        }

    @Override
    public void run() {
        if (whoAmI == 0) {
            while (true) {
                try {
                    sleep(1000);
                    stack.push(i);
                    System.out.println("pushing"+ i);
                    i++;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if(whoAmI == 1) {
            while(true){
                try{
                    sleep(500);
                    System.out.println("popping"+ stack.pop());
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Pusher pusher = new Pusher(0);
        Pusher popper = new Pusher(1);
        pusher.start();
        popper.start();
    }

}

