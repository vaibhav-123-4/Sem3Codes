class secondlargest{
    public static void sort(int arr[]){
        int n = arr.length;
        for(int i = 0; i < n-1; i++){
            for(int j = 0; j < n-i-1; j++){
                if(arr[j] > arr[j+1]){              
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

    }


 void printarr(int arr[]){
   
        System.out.print(arr[arr.length-3] + " ");

    
 }

public static void main(String[] args){
    int arr[] = {0,-1,-10,-15,20,30};
    secondlargest object = new secondlargest();
    object.sort(arr);
    object.printarr(arr);
    

}
}