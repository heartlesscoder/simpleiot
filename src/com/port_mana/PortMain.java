package com.port_mana;

import Utils.ByteUtils;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

public class PortMain {

    public static int BAUDRATE = 115200;

    public final static int DATA_String = 1;//以字符串方式发送数据
    public final static int DATA_HEX = 16;//以16进制形式发送数据
    public SerialPort mSerialport;
    public int mDataHexChoice;
    public String mData = "";

//    public static void main(String args[]){
//
//        PortMain portMain = new PortMain();
//
//        mDataHexChoice = DATA_HEX;
//        String commName = "COM5";
//        String data = new String("01");   //要发的数据
//        portMain.openSerialPort(commName, BAUDRATE);
//
//        //延迟关串口
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(11000);
//                    portMain.closeSerialPort();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//        //portMain.sendData(data);   //发数据
//    }

    /**
     * 打开串口
     * @param commName 获取串口名称
     *
     * @param baudrate 获取波特率，默认为 115200
     *            点击事件
     */
    public void openSerialPort(String commName, int baudrate) {
        // 检查串口名称是否获取正确
        if (commName == null || commName.equals("")) {
            System.out.println("没有搜索到有效串口！");
        } else {
            try {
                mSerialport = SerialPortManager.openPort(commName, baudrate);
                if (mSerialport != null) {
                    System.out.println("串口已打开" + "\r\n");
                    //此处要求页面 setText("关闭串口");
                }
            } catch (PortInUseException e) {
                System.out.println("串口已被占用！");
            }
        }

        // 添加串口监听
        SerialPortManager.addListener(mSerialport, new SerialPortManager.DataAvailableListener() {

            @Override
            public void dataAvailable() {
                byte[] data = null;
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    if (mSerialport == null) {
                        System.out.println("串口对象为空，监听失败！");
                    } else {
                        // 读取串口数据
                        data = SerialPortManager.readFromPort(mSerialport);

                        // 以字符串的形式接收数据
                        if (mDataHexChoice == DATA_String) {
                            stringBuilder.append(new String(data) + "\r\n");
                        }

                        // 以十六进制的形式接收数据
                        if (mDataHexChoice == DATA_HEX) {
                            stringBuilder.append(new String(ByteUtils.hex2ascll_2bit(data)) + "\r\n");
                        }

                        //应该传给web显示

                        mData = stringBuilder.toString();
                        System.out.println(mData);
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                    // 发生读取错误时显示错误信息后退出系统
                    System.exit(0);
                }
            }
        });
    }

    /**
     * 关闭串口
     */
    public void closeSerialPort() {
        SerialPortManager.closePort(mSerialport);
        System.out.println("串口已关闭" + "\r\n");
        //此处要求页面 setText("打开串口");
        mSerialport = null;
    }

    /**
     * 发送数据
     * @param data
     */
    public void sendData(String data) {
        // 待发送数据
        //String data = mDataInput.getText().toString();

        if (mSerialport == null) {
            System.out.println("请先打开串口！");
            return;
        }

        if ("".equals(data) || data == null) {
            System.out.println("请输入要发送的数据！");
            return;
        }

        // 以字符串的形式发送数据
        if (mDataHexChoice == DATA_String) {
            SerialPortManager.sendToPort(mSerialport, data.getBytes());
        }

        // 以十六进制的形式发送数据
        if (mDataHexChoice == DATA_HEX) {
            SerialPortManager.sendToPort(mSerialport, ByteUtils.hexStr2Byte(data));
        }
    }


}
