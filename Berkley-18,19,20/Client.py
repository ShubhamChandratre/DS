import socket, time, random

HOST=input("Enter Server IP:")
PORT=9999

c=socket.socket()
c.connect((HOST,PORT))

initial_drift=random.uniform(-5,5)
client_offset=0

print("Initial Drift:",round(initial_drift,2),"seconds")

while True:
    local=time.time()+initial_drift+client_offset

    c.send(str(local).encode())

    diff=float(c.recv(1024).decode())
    client_offset+=diff

    print("\nLocal Time:",round(local,2))
    print("\nClient Offset:",round(client_offset,4))

    time.sleep(5)
