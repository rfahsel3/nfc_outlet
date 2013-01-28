/*
  Web client
 
 This sketch connects to a website (http://www.google.com)
 using an Arduino Wiznet Ethernet shield. 
 
 Circuit:
 * Ethernet shield attached to pins 10, 11, 12, 13
 
 created 18 Dec 2009
 modified 9 Apr 2012
 by David A. Mellis
 
 */

#include <SPI.h>
#include <Ethernet.h>

// Enter a MAC address for your controller below.
// Newer Ethernet shields have a MAC address printed on a sticker on the shield
byte mac[] = {  0x90, 0xA2, 0xDA, 0x00, 0xD8, 0xF8 };
IPAddress server(128,61,105,86); // Google

// Initialize the Ethernet client library
// with the IP address and port of the server 
// that you want to connect to (port 80 is default for HTTP):
EthernetClient client;
int status=0;

void setup() {
 // Open serial communications and wait for port to open:
  Serial.begin(9600);
   while (!Serial) {
    ; // wait for serial port to connect. Needed for Leonardo only
  }
  // start the Ethernet connection:
  if (Ethernet.begin(mac) == 0) {
    Serial.println("Failed to configure Ethernet using DHCP");
    // no point in carrying on, so do nothing forevermore:
    for(;;)
      ;
  }
  // give the Ethernet shield a second to initialize:
  delay(1000);
  Serial.println("connecting...");

  // if you get a connection, report back via serial:
  if (client.connect(server, 80)) {
    Serial.println("connected");
    // Make a HTTP request:
    client.println("GET /index2.php HTTP/1.0");
    client.println();
  } 
  else {
    // kf you didn't get a connection to the server:
    Serial.println("connection failed");
  }
}

void loop()
{
  // if there are incoming bytes available 
  // from the server, read them and print them:
  //Temporary variable to keep tracke of each letter
  char c=0;
  //Counter for how long line is
  int total=0;
  //Keeps track of current line
  char line[500];
  //Auth value
  int auth=0;
  
  //Loop until carriage return is found
  while(c!=10)
  {
    if (client.available()) {
      c = client.read();
      Serial.print(c);
      line[total]=c;
      total+=1;
    }
  }
  
  //Control LED depending on output
  if(status==1)  {
    auth=line[total-2];
    //If, one on the server *(ASCII value for 1 is 49), power the led high
    if (auth==49)  {
      pinMode(6,OUTPUT);
      digitalWrite(6, HIGH);
      delay(10000);
    }
  }
  
  //When indicator is detected, take next value on next loop
  if(total==2)  {
    status=1;
  }

  // if the server's disconnected, stop the client:
  if (!client.connected()) {
    Serial.println();
    Serial.println("disconnecting.");
    client.stop();

    // do nothing forevermore:
    for(;;)
      ;
  }
}
