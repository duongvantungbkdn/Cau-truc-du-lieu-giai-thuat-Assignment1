
package mainsort;

import java.util.ArrayList;
import java.util.Scanner;

public class MainSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Algorithm alg = new Algorithm();

//        float[] a = {9.0f, 3.0f, 5.0f, 6.0f, 1.0f, 2.0f, 4.0f};
        float[] a = null;

        int choice;
        do {
            choice = menu(sc);
            switch(choice){
                case 0:
                    break;
                case 1:
                    // nhập các giá trị của mảng và lưu vào file INPUT.txt
                    alg.writeFile("INPUT.txt",input(sc));
                    break;
                case 2:
                    // xuất ra mảng a từ file INPUT.txt
                    a = alg.readFile("INPUT.txt");
                    System.out.println("Read from file");
                    System.out.print("Array a: ");
                    //hiển thị mảng a
                    alg.display(a);
                    break;
                case 3:
                    if(a != null) {

                        // sắp xếp nổi bọt
                        System.out.println("Bubble sort");
                        float[] aBubbleSort = alg.bubbleSort(a);
                        // lưu kết quả cuối cùng vào file OUTPUT1.txt
                        alg.writeFile("OUTPUT1.txt",aBubbleSort);
                    } else {
                        System.out.println("Array a is null. Please input at step 1 and output at step 2");
                    }
                    break;
                case 4:
                    if(a != null) {

                        //Sắp xếp lựu chọn
                        System.out.println("Selection sort");
                        float[] aSelectSort = alg.selectionSort(a);

                        // lưu kết quả cuối cùng vào file OUTPUT2.txt
                        alg.writeFile("OUTPUT2.txt",aSelectSort);
                    } else {
                        System.out.println("Array a is null. Please input at step 1 and output at step 2");
                    }
                    break;
                case 5:
                    if(a != null) {

                        //sắp xếp chèn
                        System.out.println("Insertion sort");
                        float[] aInsertSort = alg.insertionSort(a,true);

                        // lưu kết quả cuối cùng vào file OUTPUT3.txt
                        alg.writeFile("OUTPUT3.txt",aInsertSort);
                    } else {
                        System.out.println("Array a is null. Please input at step 1 and output at step 2");
                    }
                    break;
                case 6:
                    //Nhập chức năng tìm kiếm tuần tự.
                    if(a != null) {
                        System.out.println("Linear Search");

                        //Nhập vào giá trị cần tìm kiếm
                        System.out.print("Input value: ");
                        float value = sc.nextFloat();

                        //Tìm kiếm vị trí (chỉ số) của các phần tử trong mảng a có giá trị lớn hơn value
                        ArrayList<Integer> result = alg.search(a,value);

                        // validate nếu không tìm thấy giá trị nào thỏa mãn
                        if(result.size() != 0) {
                            //in kết quả ra màn hình
                            System.out.print("Chi so la:");
                            alg.display(result);

                            //=>Tự động in kết quả tìm kiếm vào file OUTPUT4.TXT
                            alg.writeFile("OUTPUT4.txt", alg.toString(result));
                        } else {
                            System.out.println(value + " is greater than every elements in array {" + alg.toString(a) + "}");
                        }

                    } else {
                        System.out.println("Array a is null. Please input at step 1 and output at step 2");
                    }
                    break;
                case 7:
                    //Nhập chức năng tìm kiếm nhị phân
                    if(a != null) {
                        System.out.println("Binary Search");

                        //Nhập vào giá trị cần tìm kiếm
                        System.out.print("Input value: ");
                        float value = sc.nextFloat();

                        // sắp xếp mảng theo thứ tự tăng dần
                        float[] aSort = alg.insertionSort(a,false);
                        int result = alg.binarySearch(aSort,0,a.length-1,value);

                        // validate trường hợp không tìm thấy phần tử thỏa mãn
                        if(result != -1) {

                            // tìm phần tử đầu tiên thỏa mãn
                            while(result>0 && aSort[result] == aSort[result-1]) {
                                result = result - 1;
                            }
                            //Hiển thị chỉ số của phần tử thỏa mãn  đầu tiên ra màn hình
                            System.out.print("Indext of fist element: " + result);

                            // Tự động in kết quả tìm kiếm vào file OUTPUT5.TXT
                            alg.writeFile("OUTPUT5.txt","" + result);
                        } else {
                            System.out.println(value + " is not found in array {" + alg.toString(aSort) + "}");
                        }

                    } else {
                        System.out.println("Array a is null. Please input at step 1 and output at step 2");
                    }
                    break;
                default:
                    System.out.println("**Invalid choice. Try again.**");
            }
        } while(choice != 0);
        System.out.println(" Good bye, have a nice day!");
    }

    // hàm nhập số lượng phần tử và các phần tử của mảng
    public static float[] input(Scanner sc) {
        System.out.print("Input number of array's elements : ");
        int number = sc.nextInt();
        System.out.print("Input " + number + " float elements: ");
        float[] arr = new float[number];
        for(int i = 0; i < number; i++) {
            arr[i] = sc.nextFloat();
        }
        return arr;
    }    

    //menu các chức năng lựa chọn
    public static int menu(Scanner sc) {
        System.out.println("\n+-------------------Menu------------------+");
        System.out.println("|      1.Input                            |");
        System.out.println("|      2.Output                           |");
        System.out.println("|      3.Bubble sort                      |");
        System.out.println("|      4.Selection sort                   |");
        System.out.println("|      5.Insertion sort                   |");
        System.out.println("|      6.Search > value                   |");
        System.out.println("|      7.Search = value                   |");
        System.out.println("|      0.Exit                             |");
        System.out.println("+-----------------------------------------+");
        
        System.out.println();
        System.out.print("Choice: ");
        int choice = sc.nextInt();
        
        return choice;
    }
    
}
