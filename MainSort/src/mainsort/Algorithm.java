
package mainsort;
import java.io.*;
import java.util.ArrayList;

public class Algorithm {

    // phương thức ghi mảng arr vào file fileName
    public void writeFile(String fileName, float arr[]) {
        File file = new File(fileName);
        try {
            FileOutputStream out = new FileOutputStream(file,false);

            //chuyển đổi arr thành chuỗi string rồi chuyển thành mảng byte để ghi vào file
            byte buff[] = this.toString(arr).getBytes();
            out.write(buff);
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
//            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("IOException");
//            throw new RuntimeException(e);
        }
    }

    // overloading writeFile => ghi chuỗi string str vào file fileName
    public void writeFile(String fileName, String str) {
        File file = new File(fileName);
        try {
            FileOutputStream out = new FileOutputStream(file,false);
            byte buff[] = str.getBytes();
            out.write(buff);
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    //phương thức đọc dữ liệu từ file fileName và trả về mảng float
    public float[] readFile(String fileName) {
        String string = "";
        try {
            FileInputStream input = new FileInputStream(fileName);

            // đọc ra chuỗi string trong file fileName
            int data = input.read();
            while(data != -1) {
                string += (char)data;
                data = input.read();
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.out.println("Please choose 1 to create INPUT.txt file");
        } catch (IOException e) {
            System.out.println("IOException error");
        }

        // phân tách chuỗi string thành mảng string
        String[] arrStr = string.split(", ");

        // chuyển đổi mảng string thành mảng float
        float[] arrF = new float[arrStr.length];
        for(int i = 0; i < arrStr.length; i++) {
            arrF[i] = Float.parseFloat(arrStr[i]);
        }
        return arrF;
    }

    // phương thức sắp xếp nổi bọt
    public float[] bubbleSort(float arr[]) {
        // copy array;
        float[] b = copyArr(arr);
        boolean end;
        int j = b.length - 1;
        long startTime = System.nanoTime();
        do{
            end = true;
            for(int i = 0; i < j; i++) {

                // so sánh 2 giá trị liền kề nhau,
                // nếu gía trị sau nhỏ hơn giá trị phía trước thì thực hiện đổi chỗ
                if(b[i]>b[i+1]){
                    swap(i,i+1,b);
                    end = false;
                }
            }
            display(b);
            j--;

            // nếu tất cả các giá trị ở phía trước đều nhỏ hơn giá trị ở phía sau
            // thì thoát vòng lặp do while
            // end = true;
        }while(!end);
        long endTime = System.nanoTime();
        System.out.println("Run time is " + (endTime - startTime) + " nanoseconds");
        return b;
    }

    // phương thức sắp xếp lựa chọn
    public float[] selectionSort(float arr[]) {
        // copy array to other array
        float[] b = copyArr(arr);

        long startTime = System.nanoTime();
        //selectSort
        for(int i = 0; i < b.length - 1; i++) {

            //giả sử giá trị nhỏ nhất là giá trị ở vị trí đang xét i
            int minIndex = i;
            for(int j = i +1; j < b.length; j++) {

                // tìm chỉ mục của giá trị nhỏ nhất trong khoảng từ i đến b.length-1
                if(b[j] < b[minIndex]) {minIndex = j;}
            }

            // hoán đổi giá trị nhỏ nhất với giá trị ở vị trí đang xét
            if(minIndex != i) {
                swap(minIndex,i,b);
            }
            display(b);
        }
        long endTime = System.nanoTime();
        System.out.println("Run time is " + (endTime - startTime) + " nanoseconds");
        return b;
    }

    // phương thức sắp xếp chèn
    public float[] insertionSort(float arr[],boolean isDislpaySteps) {
        // copy array to other array
        float[] b = copyArr(arr);

        long startTime = System.nanoTime();

        for(int i = 1; i < b.length; i++) {

            // lưu giá trị hiện tại đang xét ra biến current
            float current = b[i];

            // từ giá trị trước giá trị hiện tại tính về phía trước mảng
            // nếu các giá trị này lớn hơn current thì dịch các giá trị này về sau 1 vị trí
            int j = i - 1;
            while(j>=0 && b[j] >current) {
                b[j+1] = b[j];
                j--;
            }
            // nếu gặp 1 giá trị nhỏ hơn current thì lập tức đặt current vào sau giá trị này
            b[j+1] = current;
            // cho phép hoặc không cho phép hiển thị ra từng bước sắp xếp
            if (isDislpaySteps) {display(b);}
        }

        long endTime = System.nanoTime();
        System.out.println("Run time is " + (endTime - startTime) + " nanoseconds");
        return b;
    }

    // phương thức tìm kiếm vị trí các phần tử trong mảng arr có giá trị lớn hơn value
    public ArrayList<Integer> search(float arr[], float value) {
        ArrayList<Integer> search = new ArrayList<Integer>();
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] > value) {
                search.add(i);
            }
        }
        // trả về danh sách số nguyên chứa vị trí các phần tử thỏa mãn
        return search;
    }

    // phương thức tìm kiếm nhị phân
    public int binarySearch(float arr[], int left, int right, float value) {
        if(right >= left){
            // chia mảng thành 2 phần bởi vị trí ở giữa m
            int m = left + (right - left)/2;

            // nếu phần tử ở giữa mảng
            if(arr[m] == value) {return m;}

            // nếu phần tử ở giữa lớn hơn value thì value chỉ nằm ở phần bên trái
            if(arr[m] > value) {
                // gọi lại chính hàm binarySearch cho phần bên trái
                return binarySearch(arr,left,m-1,value);
            }

            // nếu phần tử ở giữa nhỏ hơn value thì value chỉ nằm ở phần bên phải
            // gọi lại chính hàm binarySearch cho phần bên phải
            return binarySearch(arr,m+1,right,value);
        }

        // nếu value ko tồn tại trong arr
        return -1;
    }

    // phương thức hiển thị mảng
    public void display(float[] arr) {
        for(float a : arr) {
            System.out.print(" " + a);           
        }
        System.out.println();
    }
    // overloadding
    public void display(ArrayList<Integer> arr) {
        for(int a : arr) {
            System.out.print(" " + a);
        }
        System.out.println();
    }

    // phương thức coppy mảng
    public float[] copyArr(float[] arr) {
        int length = arr.length;
        float[] b = new float[length];
        for(int i = 0; i < length; i++) {
            b[i] = arr[i];
        }
        return b;
    }

    // phương thức toString của 1 mảng
    public String toString(float[] arr) {
        String string = "";
        for(int i = 0; i < arr.length - 1; i++) {
            string += arr[i] + ", ";
        }
        return string + arr[arr.length - 1];
    }

    // overloading phương thức toString
    public String toString(ArrayList<Integer> arr) {
        String string = "";
        for(int i = 0; i < arr.size() - 1; i++) {
            string += arr.get(i) + ", ";
        }
        return string + arr.get(arr.size() - 1);
    }

    // phương thức đổi vị trí 2 phần tử trong mảng
    public void swap(int a, int b, float[] arr) {
        float temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
