import socket, time

HOST, PORT='',9999

S=socket.socket()
S.bind((HOST,PORT))
S.listen(1)

num_clients=int(input("Enter number of clients:"))
clients=[]

print("Waiting for Clients...")

for i in range(num_clients):
    conn,addr=S.accept()
    clients.append(conn)

server_offset=0

while True:

    times=[]

    for c in clients:
        t=float(c.recv(1024).decode())
        times.append(t)

    server_time=time.time()+server_offset

    avg=(sum(times)+server_time)/(len(times)+1)

    print("Client Times:", [round(t,2) for t in times])
    print("Server Times:",round(server_time,2))
    print("Average:",round(avg,2))
    print("------------------------------------------------------")

    for i,c in enumerate(clients):
        diff=avg-times[i]
        c.send(str(diff).encode())

    server_diff = avg-server_time
    server_offset+=server_diff

    print("Server Offset:",round(server_offset,2))

    time.sleep(5)
