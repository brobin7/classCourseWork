#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <fstream>
#include <math.h> 
#include <time.h>

using namespace std; 

unsigned char *data;
clock_t begin, end;

long stDcandidates, stDbestBegin, stDbestEnd, stDthread;

long rangeCandidates, rangeBegin, rangeEnd, rangeThread;

long changeCandidates, changeBegin, changeEnd, changeThread;

long bstdCandidates, bstdBegin, bstdEnd, bstdThread;

pthread_mutex_t mutex1 = PTHREAD_MUTEX_INITIALIZER;
long double bestDev = 127;

pthread_mutex_t mutex2 = PTHREAD_MUTEX_INITIALIZER;
int bestRange = 255;

pthread_mutex_t mutex3 = PTHREAD_MUTEX_INITIALIZER;
int bestChange = 255;

pthread_mutex_t mutex4 = PTHREAD_MUTEX_INITIALIZER;
long double bstdChange = 255;

struct readInfo{
	long carryO;
	int isCarryO;
	long numThread;
	long numBlocks;
	long start;
	long end;
	long childID;

};

void writeGraph(long double tData){
	if(tData < 1){
		cout <<"k" <<endl;
	} else{
		for(int i = 0; i < tData; i++){
			if(i == 0){
				cout<<"|";
			}

			cout<<"=";

			if(i+1 > tData){
				cout<<"x"<<endl;
			}
		}
	}

}

void printStuff(){

	cout<< "THE BEST STANDARD DEVIATION IS: " << bestDev <<endl;
	cout<< "IT BEGINS AT: " << stDbestBegin << endl;
	cout << "IT ENDS AT: " << stDbestEnd << endl;
	cout << "THE CANDIDATE THAT FOUND IT WAS: #" << stDthread <<endl;
	cout << "THE AMOUNT OF COMPLETED DEVIATION CALCULATIONS ARE: " << stDcandidates <<endl;
	cout << "THE LOWEST RANGE IS: " << bestRange << endl;
	cout << "THE CANDIDATE THAT FOUND IT WAS: #" << rangeThread << endl;
	cout << "THE AMOUNT OF COMPLETED RANGE CALCULATIONS ARE: " << rangeCandidates <<endl;
	cout << "IT BEGINS AT: " << rangeBegin << endl;
	cout << "IT ENDS AT: " << rangeEnd << endl;
	cout << "THE BEST CHANGE IS: " << bestChange << endl;
	cout << "THE CANDIDATE THAT FOUND IT WAS: #" << changeThread << endl;
	cout <<"THE AMOUNT OF COMPLETED CHANGE CALCULATIONS ARE: " << changeCandidates << endl;
	cout << "IT BEGINS AT: " << changeBegin << endl;
	cout << "IT ENDS AT: " << changeEnd << endl;
	cout << "THE STANDARD DEVIATION OF THE BEST CHANGE IS: " << bstdChange << endl;
	cout << "THE CANDIDATE THAT FOUND IT WAS #" << bstdThread << endl;
	cout << "THE AMOUNT OF COMPLETED BEST CHANGE DEVATIONS ARE: " << bstdCandidates << endl;
	cout << "IT BEGINS AT: " << bstdBegin << endl;
	cout << "IT ENDS AT: " << bstdEnd << endl;



}
void *routine( void * arg){

	struct readInfo * information = (struct readInfo*) arg;

	int rangeMin = 255;
	int rangeMax = 0;
	int range;

	long rCarryO = information->carryO;
	int rIsCarry = information->isCarryO;
	long rNumThread = information->numThread;
	long rNumBlocks = information->numBlocks;
	long rStart = information->start;
	long rChildID = information->childID;
	long rEnd = information->end;

	long double avgAnswer, tAvg;

	long double dev, tDev;

	int newVal, diffVal;

	long finalVal = 0;

	int changeID;

	long double absAvg = 0;
	long double absAns = 0;

	if(rChildID == rNumThread - 1){
		if(rIsCarry == 1){
			rNumBlocks = rNumBlocks + rCarryO;
		}
	}

	int solution[rNumBlocks];

	for(int i = 0; i < rNumBlocks; i++){

		solution[i] = (int) data [rStart + i];

	}



	if(bestChange > 0){
		for(int i = 0; i < rNumBlocks; i++){
			newVal = abs(solution[i] - solution[i+1]);

			pthread_mutex_lock( &mutex3 );
			if(newVal < bestChange){
				bestChange = newVal;
				changeBegin = rStart;
				changeEnd = rEnd;
				changeThread = rChildID + 1;
				changeCandidates = changeCandidates + 1;
			}
			pthread_mutex_unlock( &mutex3 );

		}
	}


	if(bstdChange > 0){
		for(int i = 0; i < rNumBlocks; i++){
			absAvg = abs(solution[i] - solution[i+1]) + absAvg;
		}
		absAvg = absAvg/rNumBlocks;


		for(int i = 0; i < rNumBlocks; i++){
			diffVal = abs(solution[i] - solution[i+1]);
			absAns = (diffVal - absAvg) * (diffVal - absAvg) + absAns;

		}

		absAns = sqrt(absAns/rNumBlocks);

		pthread_mutex_lock( &mutex4 );
		if(absAns < bstdChange){
			bstdChange = absAns;
			bstdBegin = rStart;
			bstdEnd = rEnd;
			bstdThread = rChildID + 1;
			bstdCandidates++;

		}

		pthread_mutex_unlock( &mutex4);
	}

	if(bestRange > 0){
		for(int i = 0; i < rNumBlocks; i++){
			if(solution[i] < rangeMin){
				rangeMin = solution[i];

			}
			if(solution[i] > rangeMax){
				rangeMax = solution[i];
			}
		}

		range = rangeMax - rangeMin;

		pthread_mutex_lock( &mutex2 );

		if (range < bestRange){
			bestRange = range;
			rangeBegin = rStart;
			rangeEnd = rEnd;
			rangeThread = rChildID + 1;
			rangeCandidates++;
		}
		pthread_mutex_unlock( &mutex2 );
	}

	if(bestDev > 0){
		avgAnswer = 0;
		for(int i = 0; i < rNumBlocks; i++){
			avgAnswer = avgAnswer + solution[i];
		}

		tAvg = avgAnswer/rNumBlocks;

		dev = 0;
		for(int i = 0; i < rNumBlocks; i++){
			dev +=(solution[i] - tAvg)*(solution[i] - tAvg);
		}

		tDev = dev/rNumBlocks;
		pthread_mutex_lock( &mutex1 );



		if(sqrt(tDev) < bestDev){
			bestDev = sqrt(tDev);
			stDbestBegin = rStart;
			stDthread = rChildID + 1;
			stDbestEnd = rEnd;
			stDcandidates++;


		}

		writeGraph(sqrt(tDev));

		pthread_mutex_unlock( &mutex1 );



	}
}

int main(int argc, char *argv[]){
	cout << "Bradley Robinson/brobin7" << endl;
	int rc, fd, nThreads, isCarryOver;


	double time_elapsed;

	long carryOver, blocks;
	char*filename = argv[1];

	nThreads = atoi(argv[2]);

	rangeCandidates = 0;
	stDcandidates = 0;
	changeCandidates = 0;
	bstdCandidates = 0;

	rangeThread = 1;
	stDthread = 1;
	changeThread = 1;
	bstdThread = 1;

	struct readInfo info[nThreads];
	long file_size;



	FILE *fp;

	fp = fopen(filename, "rb");

	if (NULL == fp) {
		cout << "BAD FILE EXITING...." << endl; 
		exit (1);
	}

	if (fseek(fp, 0 , SEEK_END) != 0) {
		cout << "BAD FILE EXITING...." << endl; 
		exit (1);
	}

	file_size = ftell(fp);
	fclose(fp);

	if(file_size % nThreads != 0){

		carryOver = file_size % nThreads;

		blocks = file_size/nThreads;
		isCarryOver = 1;
	} 
	else{

		blocks = file_size/nThreads;
		isCarryOver = 0;
	}

	FILE *sp;
	sp = fopen(filename, "rb");
	if (NULL == sp) {
		cout << "BAD FILE EXITING...." << endl; 
		exit (1);
	}



	data = (unsigned char*) malloc(file_size);


	fread(data, 1, file_size, sp);

	fclose(sp);

	pthread_t * tid = new pthread_t [nThreads];

	begin = clock();
	for(int i = 0 ; i < nThreads; i++){

		info[i].isCarryO = isCarryOver;
		info[i].carryO = carryOver;
		info[i].numBlocks = blocks;
		info[i].numThread = nThreads;

		info[i].childID = i;
		info[i].start = blocks * i;
		info[i].end = (info[i].start + blocks) - 1;

		pthread_create(&tid[i], NULL, &routine, &info[i]);


	}
	for(int i = 0 ; i < nThreads; i++){

		pthread_join(tid[i], NULL);

	}
	end = clock();

	time_elapsed = (double)(end - begin) / CLOCKS_PER_SEC;
	cout << endl;	
	cout <<"NUMBER OF TOTAL THREADS: " << nThreads << endl;	
	cout <<"BLOCK SIZE: " << blocks << endl;
	cout <<"THE CARRY OVER USED IN THE LAST THREAD IS: " << carryOver << endl;
	cout <<"THE TOTAL TIME IT TOOK IS: " << time_elapsed <<endl;
	
	printStuff();

}
